public class HashTable {

    final static String INVALID_INDEX = "올바르지 않은 인덱스입니다.";
    final static String EMPTY_DATA = "데이터가 입력되지 않았습니다.";

    Data[] hashTable;
    public HashTable(int size) {
        hashTable = new Data[size];
    }

    public class Data{
        String key;
        String value;
        Data next;

        public Data(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public int size() {
        return this.hashTable.length;
    }

    private int findIndex(String key) {
        return key.charAt(0) % this.size();
    }

    public void put(String key, String value) {
        int idx = this.findIndex(key);
        Data newData = new Data(key, value);

        //idx에 데이터가 처음 들어감.
        if (hashTable[idx] == null) {
            hashTable[idx] = newData;
            return;
        }

        //이미 idx에 데이터가 있음(LinkedList).
        Data data = hashTable[idx];
        if (checkDuplication(data, newData)) return; //head 중복 key 확인
        while (data.next != null) { //head 부터 tail까지 중복 key 확인. 중복 key 값을 가진 데이터는 덮어씀.
            data = data.next;
            if (checkDuplication(data, newData)) return;
        }

        data.next = newData; //next값이 없는 가장 마지막 data 뒤에 새로운 data를 적재.
    }

    public String get(String key) {
        int index = this.findIndex(key);
        //data의 key에 해당하는 데이터가 적재되지 않음.
        if (hashTable[index] == null) {
            return EMPTY_DATA;
        }

        //data가 적재 되어있음. 해당 인덱스 데이터의 head 부터 tail까지 탐색
        Data data = hashTable[index];
        while (data != null) {
            if (key.equals(data.key)) {
                return data.value;
            }
            data = data.next;
        }

        return EMPTY_DATA;
    }

    public String get(int index) {
        StringBuilder sb = new StringBuilder();

        if (index < 0 || index > this.size()) {
            return INVALID_INDEX;
        }
        if (hashTable[index] == null) {
            return EMPTY_DATA;
        }

        Data data = hashTable[index];
        while (data != null) {
            sb.append(data.value);
            sb.append(" ");
            data = data.next;
        }
        return sb.toString();
    }

    public boolean checkDuplication(Data prevData, Data newData) {
        if (prevData.key.equals(newData.key)) {
            prevData.value = newData.value;
            return true;
        }
        return false;
    }
}