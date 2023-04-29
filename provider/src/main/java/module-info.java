import org.example.provider.SwedishKrona;
import org.example.provider.Dollar;
import org.example.provider.HRK;
import org.example.service.CurrencyConverter;

module org.example.provider {

    exports org.example.provider;

    requires org.example.service;
    requires com.google.gson;

    provides CurrencyConverter with Dollar, SwedishKrona, HRK;

}