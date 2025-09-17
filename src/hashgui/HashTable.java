/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashgui;

import java.util.Arrays;

public class HashTable {
    private String[] table;
    private int size;
    private String lastLog = "";

    public HashTable(int size) {
        this.size = size;
        this.table = new String[size];
        Arrays.fill(table, null);
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % size;
    }

    public boolean insert(String key) {
        lastLog = "Insertando '" + key + "'\n";
        int h = hash(key);
        for (int i = 0; i < size; i++) {
            int idx = (h + i) % size; // linear probing
            if (table[idx] == null) {
                table[idx] = key;
                lastLog += "Insertado en índice " + idx + "\n";
                return true;
            } else {
                lastLog += "Colisión en índice " + idx + "\n";
            }
        }
        lastLog += "Tabla llena, no se pudo insertar.\n";
        return false;
    }

    public boolean search(String key) {
        lastLog = "Buscando '" + key + "'\n";
        int h = hash(key);
        for (int i = 0; i < size; i++) {
            int idx = (h + i) % size;
            if (table[idx] == null) {
                lastLog += "Vacío en índice " + idx + " → detener\n";
                return false;
            }
            if (table[idx].equals(key)) {
                lastLog += "Encontrado en índice " + idx + "\n";
                return true;
            }
        }
        lastLog += "No encontrado\n";
        return false;
    }

    public boolean delete(String key) {
        lastLog = "Eliminando '" + key + "'\n";
        int h = hash(key);
        for (int i = 0; i < size; i++) {
            int idx = (h + i) % size;
            if (table[idx] == null) return false;
            if (table[idx].equals(key)) {
                table[idx] = null;
                lastLog += "Eliminado de índice " + idx + "\n";
                return true;
            }
        }
        lastLog += "No encontrado\n";
        return false;
    }

    public String[] snapshot() {
        return table;
    }

    public String getLastLog() {
        return lastLog;
    }
}
