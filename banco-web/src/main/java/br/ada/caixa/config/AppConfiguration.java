package br.ada.caixa.config;

import br.ada.caixa.dto.request.RegistrarClientePFRequestDto;
import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper getModelMapper() {
        Converter<String, LocalDate> dateConverter = new AbstractConverter<>() {
            protected LocalDate convert(String source) {
                return source == null ? null : LocalDate.parse(source);
            }
        };

        Converter<LocalDate, String> dateStringConverter = new AbstractConverter<>() {
            protected String convert(LocalDate source) {
                return source == null ? null : source.toString(); //yyyy-MM-dd
            }
        };

        //yyyy-MM-dd 00:44:00

        var modelMapper = new ModelMapper();

        modelMapper.typeMap(RegistrarClientePFRequestDto.class, ClientePF.class)
                .addMapping(RegistrarClientePFRequestDto::getCpf, ClientePF::setDocumento);

        modelMapper.typeMap(ClientePF.class, ClientePFResponseDto.class)
                        .addMapping(ClientePF::getDocumento, ClientePFResponseDto::setCpf);

        modelMapper.typeMap(RegistrarClientePJRequestDto.class, ClientePJ.class)
                .addMapping(RegistrarClientePJRequestDto::getCnpj, ClientePJ::setDocumento);

        modelMapper.addConverter(dateConverter);
        modelMapper.addConverter(dateStringConverter);
        return modelMapper;
    }

}
