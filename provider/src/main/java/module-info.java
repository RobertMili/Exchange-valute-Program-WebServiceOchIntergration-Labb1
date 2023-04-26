import org.example.provider.Dollar;
import org.example.provider.EUR;
//import org.example.provider.HRK;
//import org.example.provider.Dollar;
import org.example.provider.HRK;
import org.example.service.CurrencyConverter;

module org.example.provider {

    requires org.example.service;

    exports org.example.provider;

    provides CurrencyConverter with  EUR, Dollar, HRK;

}