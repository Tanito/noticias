package com.henry.noticiero.service;

import com.henry.noticiero.model.Notice;
import com.henry.noticiero.model.Writer;
import com.henry.noticiero.repository.NoticeRepository;
import com.henry.noticiero.utils.EntityURLBuilder;
import com.henry.noticiero.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NoticeService {

    private static final String NOTICE_PATH = "notice";
    @Autowired
    private NoticeRepository noticeRepository;

    public PostResponse addNotice(Notice notice) {
        Notice savedNotice = noticeRepository.save(notice);
        return PostResponse.builder()
                .status(HttpStatus.CREATED)
                .url(EntityURLBuilder.buildURL(NOTICE_PATH, savedNotice.getId().toString()))
                .build();
    }

    public List<Notice> getAll() {
        return noticeRepository.findAll();
    }

    public Notice findById(Integer id) {
        return noticeRepository.findById(id).orElse(null);
    }

    public String editNotice(Notice notice) {
        try {
            Notice noticeFound = noticeRepository.findById(notice.getId()).orElse(null);
            Writer writerToSet = noticeFound.getWriter();
            notice.setWriter(writerToSet);
            noticeRepository.save(notice);
            return "La noticia fue editada con éxito";
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "La noticia no fue encontrada", e);
        }
    }

    public Notice deleteById(Integer id) {
        Notice deletedNotice = noticeRepository.findById(id).orElse(null);
        if (deletedNotice != null) {
            noticeRepository.deleteById(id);
            return deletedNotice;
        } else {
            return null;
        }
    }

    public List<Notice> findByWriter(Integer id) {
        List<Notice> notices = noticeRepository.findAll();
        List<Notice> noticesByWriter = new ArrayList<>();
        for (Notice n : notices) {
            if (!Objects.isNull(n.getWriter()) && n.getWriter().getId() == id) {
                noticesByWriter.add(n);
            }
        }
        if (noticesByWriter.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay noticias de ese escritor");
        }
        return noticesByWriter;
    }
}
