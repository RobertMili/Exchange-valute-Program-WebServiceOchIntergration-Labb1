import org.example.provider.SwedishKrona;
import org.example.provider.Dollar;
//import org.example.provider.HRK;
//import org.example.provider.Dollar;
import org.example.provider.HRK;
import org.example.service.CurrencyConverter;

module org.example.provider {

    requires org.example.service;
    requires com.google.gson;

    exports org.example.provider;

    provides CurrencyConverter with Dollar, SwedishKrona, HRK;

}