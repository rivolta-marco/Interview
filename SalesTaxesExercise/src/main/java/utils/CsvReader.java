package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader<T> {

    List<String> header;
    List<T> values;

    private void setHeader(String[] header) {
        this.header = Arrays.asList(header);
        this.values = new ArrayList<T>();
    }

    public List<T> process(String file, Class<T> t) throws Exception {
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                T obj = t.newInstance();

                String[] elements = line.split(",");
                if(firstLine) {
                    setHeader(elements);
                    firstLine = false;
                } else {
                    processLine(elements, t, obj);

                    values.add(obj);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("No file available");
        } catch (IOException e) {
            System.out.println("Error reading the file");
        } catch (NoSuchFieldException e) {
            System.out.println("Invalid field name, please check");
        } catch (IllegalAccessException e) {
            System.out.println("Illegal access to the desired fields");
        }
        return values.isEmpty() ? null : values;
    }

    private void processLine(String[] values, Class<T> t, T obj) throws Exception {

        for (String name : header) {

            Field field = t.getDeclaredField(name);
            field.setAccessible(true);

            int index = header.indexOf(name);

            // Here actually only the data types used in the exercise have been considered, for completeness it should be extended
            if (field.getType().isEnum())
                field.set(obj, Enum.valueOf((Class<Enum>) field.getType(), values[index]));
            else if (field.getType() == Float.class || field.getType() == float.class)
                field.setFloat(obj, Float.valueOf(values[index]));
            else if (field.getType() == Boolean.class || field.getType() == boolean.class)
                field.setBoolean(obj, Boolean.valueOf(values[index]));
            else if (field.getType() == Integer.class || field.getType() == int.class)
                field.setInt(obj, Integer.valueOf(values[index]));
            else
                field.set(obj, values[index]);

        }
    }

}