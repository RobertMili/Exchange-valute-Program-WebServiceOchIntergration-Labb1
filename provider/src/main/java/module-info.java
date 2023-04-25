import org.example.provider.Euro;
import org.example.provider.HRK;
import org.example.provider.Dollar;
import org.example.service.CurrencyConvert;

module org.example.provider {

    requires org.example.service;

    provides CurrencyConvert with Dollar, Euro, HRK;

}