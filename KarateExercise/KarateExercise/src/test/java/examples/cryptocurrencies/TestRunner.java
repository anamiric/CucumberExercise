package examples.cryptocurrencies;

import com.intuit.karate.junit5.Karate;

class TestRunner {
    
    @Karate.Test
    Karate testCryptocurrencies() {
        return new Karate().feature("cryptocurrencies").relativeTo(getClass());
    }    

}

