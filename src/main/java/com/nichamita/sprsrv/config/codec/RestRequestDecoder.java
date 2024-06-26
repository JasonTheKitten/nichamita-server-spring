package com.nichamita.sprsrv.config.codec;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageDecoder;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MimeType;

import com.nichamita.sprsrv.json.rest.request.RestRequestSerializationUtil;

import dev.mccue.json.Json;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RestRequestDecoder implements HttpMessageDecoder<Object> {

    @Override
    public boolean canDecode(ResolvableType elementType, MimeType mimeType) {
        return
            (mimeType == null || mimeType.equals(MediaType.APPLICATION_JSON))
            && RestRequestSerializationUtil.canDeserialize(elementType.getRawClass());
    }

    @Override
    public Flux<Object> decode(Publisher<DataBuffer> inputStream, ResolvableType elementType, MimeType mimeType, Map<String, Object> hints) {
        return Flux.from(inputStream)
            .map(DataBuffer::asInputStream)
            .map(InputStreamReader::new)
            .flatMap(reader -> {
                try {
                    return Mono.just(Json.read(reader));
                } catch (Exception e) {
                    return Mono.error(e);
                }
            })
            .map(json -> RestRequestSerializationUtil.deserialize(elementType.getRawClass(), json));
    }

    @Override
    public Mono<Object> decodeToMono(Publisher<DataBuffer> inputStream, ResolvableType elementType, MimeType mimeType, Map<String, Object> hints) {
        return decode(inputStream, elementType, mimeType, hints).singleOrEmpty();
    }

    @Override
    public List<MimeType> getDecodableMimeTypes() {
        return List.of(MediaType.APPLICATION_JSON);
    }

    @Override
    public Map<String, Object> getDecodeHints(
        ResolvableType actualType, ResolvableType elementType, ServerHttpRequest request, ServerHttpResponse response
    ) {
        return Map.of();
    }

}