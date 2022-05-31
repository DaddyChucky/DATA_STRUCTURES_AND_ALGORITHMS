package tests;

public class HashMapTester {
    public static Double start (){
        double total = 0;

        total += Corrector.executeUnitTest("putReturnsNullIfNoOldValue", HashMapTester::putReturnsNullIfNoOldValue, 0.25);
        total += Corrector.executeUnitTest("putReturnsOldValue", HashMapTester::putReturnsOldValue, 1.0);

        total += Corrector.executeUnitTest("get", HashMapTester::get, 1.0);
        total += Corrector.executeUnitTest("putReplacesValue", HashMapTester::putReplacesValue, 1.0);

        total += Corrector.executeUnitTest("removeReturnsNullIfNotExist", HashMapTester::removeReturnsNullIfNotExist, 0.25);
        total += Corrector.executeUnitTest("removeReturnsRemovedValue", HashMapTester::removeReturnsRemovedValue, 1.0);

        total += Corrector.executeUnitTest("sizeIncrementsAndDecrements", HashMapTester::sizeIncrementsAndDecrements, 0.5);

        total += Corrector.executeUnitTest("containsKey", HashMapTester::containsKey, 0.5);
        total += Corrector.executeUnitTest("isEmpty", HashMapTester::isEmpty, 1.0);

        total += Corrector.executeUnitTest("collisionsAreHandled", HashMapTester::collisionsAreHandled, 2.0);

        total += Corrector.executeUnitTest("clear", HashMapTester::clear, 0.5);

        total += Corrector.executeUnitTest("capacityIncreasesWithLoadFactor", HashMapTester::capacityIncreasesWithLoadFactor, 0.5);
        total += Corrector.executeUnitTest("rehashWorksProperly", HashMapTester::rehashWorksProperly, 2.0);

        return total;
    }

    public static boolean putReturnsNullIfNoOldValue(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>();
        Integer value = map.put("myKey", 1);

        return value == null;
    }

    public static boolean get(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>();

        boolean nullWithoutValue = map.get("myKey") == null;

        Integer value = 1;
        map.put("myKey", value);
        Integer valueInMap = map.get("myKey");

        return value.equals(valueInMap) && nullWithoutValue;
    }

    public static boolean putReturnsOldValue(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>();

        Integer oldValue = 1;
        map.put("myKey", oldValue);

        Integer returnedOldValue = map.put("myKey", 2);

        return oldValue.equals(returnedOldValue);
    }

    public static boolean putReplacesValue(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>();

        Integer value = 2;
        map.put("myKey", 1);
        map.put("myKey", value);
        Integer valueInMap = map.get("myKey");

        return value.equals(valueInMap) && map.size() == 1;
    }

    public static boolean removeReturnsRemovedValue(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>();

        Integer value = 1;
        map.put("myKey", value);
        Integer removedValue = map.remove("myKey");

        return value.equals(removedValue);
    }

    public static boolean removeReturnsNullIfNotExist(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>();

        Integer removedValue = map.remove("myKey");

        return removedValue == null;
    }

    public static boolean sizeIncrementsAndDecrements(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>();

        boolean isProperSizeInitially = map.isEmpty();

        map.put("myKey", 1);

        boolean isProperSizeAfterAdding = map.size() == 1;

        map.remove("myKey");

        boolean isProperSizeAfterRemoving = map.isEmpty();

        return isProperSizeInitially && isProperSizeAfterAdding && isProperSizeAfterRemoving;
    }

    public static boolean collisionsAreHandled(){
        tp2.HashMap<KeyMock, Integer> map = new tp2.HashMap<KeyMock, Integer>();
        int n = 9;

        for (int i = 0; i < n ; ++i){
            String index = String.valueOf(i);
            map.put(new KeyMock("myKey" + index), i);
        }

        boolean canGetAllValues = true;
        for (int i = 0; i < n && canGetAllValues; ++i){
            String index = String.valueOf(i);
            Integer value = map.get(new KeyMock("myKey" + index));
            canGetAllValues = value != null && value.equals(i);
        }

        boolean canDetectAllKeys = true;
        for (int i = 0; i < n && canGetAllValues && canDetectAllKeys; ++i){
            String index = String.valueOf(i);
            canDetectAllKeys = map.containsKey(new KeyMock("myKey" + index));
        }

        // Did not want tests to have any randomness, so the array is manually created
        // Does not dynamically change with n
        int[] staticRandomOrderNums = {0,4,2,6,3,1,7,8,5};
        boolean canRemoveAllValues = true;
        for (int i = 0; i < n && canGetAllValues && canDetectAllKeys && canRemoveAllValues; ++i){
            String index = String.valueOf(staticRandomOrderNums[i]);
            Integer removedValue = map.remove(new KeyMock("myKey" + index));
            canRemoveAllValues = removedValue != null && removedValue.equals(staticRandomOrderNums[i]);
        }

        return canGetAllValues && canDetectAllKeys && canRemoveAllValues;
    }

    public static boolean clear(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>();
        int n = 9;

        for (int i = 0; i < n ; ++i){
            String index = String.valueOf(i);
            map.put("myKey" + index, i);
        }

        boolean canGetAllValues = true;
        for (int i = 0; i < n && canGetAllValues; ++i){
            String index = String.valueOf(i);
            Integer value = map.get("myKey" + index);
            canGetAllValues = value != null && value.equals(i);
        }

        map.clear();

        boolean valuesCleared = canGetAllValues && map.isEmpty();
        for (int i = 0; i < n && valuesCleared; ++i){
            String index = String.valueOf(i);
            valuesCleared = map.get("myKey" + index) == null;
        }

        return valuesCleared;
    }

    public static boolean containsKey(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>();

        map.put("myKey", 1);

        return map.containsKey("myKey") && !map.containsKey("unknownKey");
    }

    public static boolean isEmpty(){
        double total = 0.0;

        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>();

        boolean isEmptyOnEmptyMap = map.isEmpty();

        map.put("myKey", 1);

        boolean isEmptyOnFilledMap = map.isEmpty();

        map.remove("myKey");

        boolean isEmptyOnEmptiedMap = map.isEmpty();

        return isEmptyOnEmptyMap && !isEmptyOnFilledMap && isEmptyOnEmptiedMap;
    }

    public static boolean capacityIncreasesWithLoadFactor(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>(10);

        int n = 6;
        for (int i = 0; i < n ; ++i){
            String index = String.valueOf(i);
            map.put("myKey" + index, i);
        }

        // Clear should not put capacity back to its initial value
        map.clear();

        return map.capacity() == 20;
    }

    public static boolean rehashWorksProperly(){
        tp2.HashMap<String, Integer> map = new tp2.HashMap<String, Integer>(10);
        int n = 15;

        for (int i = 0; i < n ; ++i){
            String index = String.valueOf(i);
            map.put("myKey" + index, i);
        }

        boolean isValidRehash = map.size() == n;
        for (int i = 0; i <  n && isValidRehash; ++i){
            String index = String.valueOf(i);
            Integer value = map.get("myKey" + index);
            isValidRehash = value != null && map.get("myKey" + index) == i;
        }

        return isValidRehash;
    }

    static class KeyMock {
        private final String key;

        public KeyMock(String key){
            this.key = key;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object other)
        {
            boolean isEqual = false;

            if (other instanceof KeyMock)
            {
                KeyMock otherKey = (KeyMock) other;
                isEqual = key.equals(otherKey.key);
            }

            return isEqual;
        }
    }
}
