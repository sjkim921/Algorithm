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

    /**
     * Chaining 충돌 방지 알고리즘을 적용한 데이터 적재 메소드
     * @param key
     * @param value
     */
    public void chainingPut(String key, String value) {
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

    /**
     * Chaining 충돌 방지 알고리즘을 이용한 key 기준 조회 메소드
     * @param key
     * @return
     */
    public String chainingGet(String key) {
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

    /**
     * Chaining 충돌 방지 알고리즘을 이용한 index 기준 조회 메소드
     * @param index
     * @return
     */
    public String chainingGet(int index) {
        StringBuilder sb = new StringBuilder();
        if (!isValidIndex(index)) return null;
        if (isNull(hashTable[index])) return null;

        Data data = hashTable[index];
        while (data != null) {
            sb.append(data.value);
            sb.append(" ");
            data = data.next;
        }
        return sb.toString();
    }

    /**
     * Linear Probing 충돌 방지 알고리즘을 적용한 데이터 적재 메소드
     * @param key
     * @param value
     */
    public void lpPut(String key, String value) {
        int index = this.findIndex(key);
        Data newData = new Data(key, value);

        //index에 해당하는 데이터 첫 적재
        if (hashTable[index] == null) {
            hashTable[index] = newData;
            return;
        }

        //index에 이미 데이터가 적재중이면 뒤의 비어있는 index에 적재
        Data data = hashTable[index];
        while (data != null) {
            checkDuplication(data, newData);
            data = hashTable[++index];
            if (index == this.size()) {
                System.out.println(INVALID_INDEX);
                return;
            }
        }
        hashTable[index] = newData;
    }

    /**
     * Linear Probing 충돌 방지 알고리즘을 적용한 key 기준 데이터 조회 메소드
     * @param key
     * @return
     */
    public String lpGet(String key) {
        int index = this.findIndex(key);
        if (isNull(hashTable[index])) return EMPTY_DATA;

        Data data = hashTable[index];
        while (data != null) {
            if (key.equals(data.key)) {
                return data.value;
            }
            data = hashTable[++index];
        }
        return EMPTY_DATA;
    }

    /**
     * Linear Probing 충돌 방지 알고리즘을 적용한 index 기준 데이터 조회 메소드
     * @param index
     * @return
     */
    public String lpGet(int index) {
        if (!isValidIndex(index)) return null;
        if (isNull(hashTable[index])) return null;

        return hashTable[index].value;
    }

    public boolean checkDuplication(Data prevData, Data newData) {
        if (prevData.key.equals(newData.key)) {
            prevData.value = newData.value;
            return true;
        }
        return false;
    }

    public boolean isNull(Data data) {
        if (data == null) {
            System.out.println(EMPTY_DATA);
            return true;
        }
        return false;
    }

    public boolean isValidIndex(int index) {
        if (index < 0 || index > this.size()) {
            System.out.println(INVALID_INDEX);
            return false;
        }
        return true;
    }
}