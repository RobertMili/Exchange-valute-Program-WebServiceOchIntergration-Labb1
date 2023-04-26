import org.example.service.annotation.CurrencyAnnotation;

module org.example.consumer {
    requires org.example.service;
    uses org.example.service.CurrencyConverter;
    uses CurrencyAnnotation;
}