import org.example.service.CurrencyConvert;

module org.example.consumer {
    requires org.example.service;
    uses CurrencyConvert;
}