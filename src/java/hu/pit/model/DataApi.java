package hu.pit.model;

import java.util.List;

public class DataApi {

    private final FileReader fileReader;
    private final DataParser dataParser;

    public DataApi(FileReader fileReader, DataParser dataParser) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
    }

    public List<Integer> getData(String filename) {
        return dataParser.parse(fileReader.read(filename));
    }
}
