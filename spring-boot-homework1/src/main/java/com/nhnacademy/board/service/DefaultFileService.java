package com.nhnacademy.board.service;

import com.nhnacademy.board.entity.Board;
import com.nhnacademy.board.entity.File;
import com.nhnacademy.board.exception.BoardNotFoundException;
import com.nhnacademy.board.repository.BoardRepository;
import com.nhnacademy.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultFileService implements FileService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    @Override
    @Transactional
    public void uploadFiles(long boardId, List<MultipartFile> uploadFiles, String uploadDir) throws IOException {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("board not found : " + boardId));

        List<String> fileDirs = getFileDirs(uploadDir, uploadFiles);
        if (!fileDirs.isEmpty()) {
            for (String fileDir : fileDirs) {
                File file = new File();
                file.setFilename(fileDir);
                file.setBoard(board);
                fileRepository.save(file);
            }
        }
    }

    private List<String> getFileDirs(String uploadDir, List<MultipartFile> files) throws IOException {
        List<String> fileDirs;
        if (!files.get(0).isEmpty()) {
            for (MultipartFile file : files) {
                file.transferTo(Paths.get(uploadDir + file.getOriginalFilename()));
                log.info("file={}", file.getOriginalFilename());
            }

            fileDirs = files.stream()
                    .map(MultipartFile::getOriginalFilename)
                    .collect(Collectors.toList());
        } else {
            fileDirs = new ArrayList<>();
        }
        return fileDirs;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> downloadFiles(long boardId) {
        return fileRepository.findAllByBoard_BoardId(boardId)
                .stream()
                .map(File::getFilename)
                .collect(Collectors.toList());
    }
}
