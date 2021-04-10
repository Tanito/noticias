package com.henry.noticiero.service;

import com.henry.noticiero.model.Notice;
import com.henry.noticiero.model.Writer;
import com.henry.noticiero.repository.WriterRepository;
import com.henry.noticiero.utils.EntityURLBuilder;
import com.henry.noticiero.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class WriterService {
    private static final String WRITER_PATH = "notice";

    //@Autowired --> Creamos el constructor, es como poner un Autowired a cada una de las líneas que están abajo: 15 y 16
    private WriterRepository writerRepository;
    private NoticeService noticiaService;

    @Autowired
    public WriterService(WriterRepository writerRepository, NoticeService noticiaService) {
        this.writerRepository = writerRepository;
        this.noticiaService = noticiaService;
    }


    public PostResponse addWriter(Writer writer) {
        Writer savedWriter = writerRepository.save(writer);
        return PostResponse.builder()
                .status(HttpStatus.CREATED)
                .url(EntityURLBuilder.buildURL(WRITER_PATH, savedWriter.getId().toString()))
                .build();

            }

    public Writer getWriter(Integer id) {

        return writerRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Writer> getAll(){
        return writerRepository.findAll();
    }

    public void addNoticia(Integer id, Integer noticeID) {
        Writer writer = getWriter(id);
        Notice notice = noticiaService.findById(noticeID);

        notice.setWriter(writer);
 //       writer.getNoticeList().add(notice);
        writerRepository.save(writer);
    }

}