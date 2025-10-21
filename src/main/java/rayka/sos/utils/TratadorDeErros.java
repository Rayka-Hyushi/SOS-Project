package rayka.sos.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroDadosInvalidos(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        List<DadosErroValidacao> dados = new ArrayList<>();
        for (FieldError fe : errors) {
            dados.add(new DadosErroValidacao(fe.getField(), fe.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(dados);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity tratarErro500(Exception ex) {
        System.out.println("Erro interno no servidor: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private record DadosErroValidacao(String campo, String mensagem) {}
}
