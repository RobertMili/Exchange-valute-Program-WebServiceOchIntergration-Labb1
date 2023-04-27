import org.example.provider.SvenskKrona;
import org.example.provider.Dollar;
//import org.example.provider.HRK;
//import org.example.provider.Dollar;
import org.example.provider.HRK;
import org.example.service.CurrencyConverter;

module org.example.provider {

    requires org.example.service;
//    requires org.json;
    requires com.google.gson;

    exports org.example.provider;

    provides CurrencyConverter with Dollar, SvenskKrona, HRK;

}