package hu.testathon;

import hu.testathon.controller.TestService;
import hu.testathon.model.domain.TestResult;
import hu.testathon.model.domain.Validator;
import hu.testathon.model.service.*;

import java.util.List;
import java.util.Scanner;

public class App {

    private final Console console;
    private final DataWriter dataWriter;
    private final TestService testService;

    private App() {
        console = new Console(new Scanner(System.in));
        dataWriter = new DataWriter("pontok.txt");
        DataParser dataParser = new DataParser();
        FileDataReader fileDataReader = new FileDataReader("valaszok.txt");
        DataReader dataReader = new DataReader(dataParser, fileDataReader);
        Validator validator = dataReader.getValidator();
        List<TestResult> testResults = dataReader.getTestResult();
        testService = new TestService(validator, testResults);
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("1. feladat: Az adatok beolvasása");
        System.out.println("2. feladat: A vetélkedőn " + testService.getCompetitorNumber() + " versenyző indult el");
        System.out.print("3. feladat: A versenyző azonosítója = ");
        String id = console.read();
        System.out.println(testService.getAnswerById(id) + "\t(a versenyző válasza)");
        System.out.println("4. feladat");
        System.out.println(testService.getCheckedResult(id));
        System.out.print("5. feladat: A feladat sorszáma = ");
        int taskNumber = console.readInt();
        System.out.println(testService.getCorrectAnswerStatistic(taskNumber));
        System.out.println("6. feladat: A versenyzők pontszámának meghatározása");
        dataWriter.printAll(testService.getFinalScores());
        System.out.println("7. feladat: A verseny legjobbjai:");
        System.out.println(testService.getOrderedResults());
    }


}
