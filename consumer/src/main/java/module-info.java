import org.example.service.annotation.CurrencyAnnotation;

module org.example.consumer {
    requires org.example.service;
    requires org.example.provider;
    uses org.example.service.CurrencyConverter;
    uses CurrencyAnnotation;
}