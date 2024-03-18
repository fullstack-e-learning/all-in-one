package net.samitkumar.allinoneapi.services;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.nio.file.Path;


public interface FileService {
    Mono<Void> upload(FilePart filePart);
    Mono<ByteBuffer> download(String fileName);
    Mono<ByteBuffer> view(String fileName);
    Flux<String> listAllFile();
}
