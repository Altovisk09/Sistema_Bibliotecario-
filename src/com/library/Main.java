package com.library;

import com.library.service.LibraryService;
import com.library.service.ReportService;
import com.library.view.RunMenu;
import com.library.util.DataSeeder;

public class Main {
    public static void main(String[] args) {
        // Instanciando os serviços
        LibraryService service = new LibraryService();
        ReportService report = new ReportService(service);

        //Dados simulados
        DataSeeder.seed(service);

        // Iniciando o menu interativo
        RunMenu runMenu = new RunMenu(service, report);
        runMenu.start();
    }
}
