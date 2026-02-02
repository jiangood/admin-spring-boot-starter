package io.github.jiangood.openadmin.lang;

import com.google.common.collect.*;
import com.google.common.base.Strings;
import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

/**
 * 谷歌工具类索引
 * 主要时谷歌工具名老记不住，这里列出一些常用的
 */
public class GoogleTool {

    // 使用谷歌工具类创建map,其中值为列表
    public static <V> Multimap<String, V> newMultimap() {
        return LinkedHashMultimap.create();
    }

    // 创建不可变List
    public static <E> List<E> immutableList(E... elements) {
        return ImmutableList.copyOf(elements);
    }

    // 创建不可变Set
    public static <E> Set<E> immutableSet(E... elements) {
        return ImmutableSet.copyOf(elements);
    }

    // 创建不可变Map
    public static <K, V> Map<K, V> immutableMap(Object... keyValuePairs) {
        Preconditions.checkArgument(keyValuePairs.length % 2 == 0, "键值对数量必须为偶数");
        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            @SuppressWarnings("unchecked")
            K key = (K) keyValuePairs[i];
            @SuppressWarnings("unchecked")
            V value = (V) keyValuePairs[i + 1];
            builder.put(key, value);
        }
        return builder.build();
    }

    // 创建哈希表
    public static <K, V> Map<K, V> newHashMap() {
        return Maps.newHashMap();
    }

    // 创建LinkedHashMap
    public static <K, V> Map<K, V> newLinkedHashMap() {
        return Maps.newLinkedHashMap();
    }

    // 创建TreeMap
    public static <K extends Comparable<? super K>, V> Map<K, V> newTreeMap() {
        return Maps.newTreeMap();
    }

    // 创建HashSet
    public static <E> Set<E> newHashSet() {
        return Sets.newHashSet();
    }

    // 创建LinkedHashSet
    public static <E> Set<E> newLinkedHashSet() {
        return Sets.newLinkedHashSet();
    }

    // 创建TreeSet
    public static <E extends Comparable<? super E>> Set<E> newTreeSet() {
        return Sets.newTreeSet();
    }

    // 创建ArrayList
    public static <E> List<E> newArrayList() {
        return Lists.newArrayList();
    }

    // 创建LinkedList
    public static <E> List<E> newLinkedList() {
        return Lists.newLinkedList();
    }

    // 字符串工具：检查字符串是否为null或空
    public static boolean isNullOrEmpty(String string) {
        return Strings.isNullOrEmpty(string);
    }

    // 字符串工具：重复字符串
    public static String repeat(String string, int count) {
        return Strings.repeat(string, count);
    }

    // 字符串工具：填充字符串到指定长度
    public static String padStart(String string, int minLength, char padChar) {
        return Strings.padStart(string, minLength, padChar);
    }

    // 字符串工具：填充字符串到指定长度
    public static String padEnd(String string, int minLength, char padChar) {
        return Strings.padEnd(string, minLength, padChar);
    }

    // 对象工具：检查对象是否为null
    public static <T> T checkNotNull(T reference) {
        return Preconditions.checkNotNull(reference);
    }

    // 对象工具：检查对象是否为null，并指定错误信息
    public static <T> T checkNotNull(T reference, String errorMessage) {
        return Preconditions.checkNotNull(reference, errorMessage);
    }

    // 条件工具：检查条件是否为true
    public static void checkArgument(boolean condition) {
        Preconditions.checkArgument(condition);
    }

    // 条件工具：检查条件是否为true，并指定错误信息
    public static void checkArgument(boolean condition, String errorMessage) {
        Preconditions.checkArgument(condition, errorMessage);
    }

    // 集合工具：检查集合是否为null或空
    public static boolean isCollectionEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    // 集合工具：检查Map是否为null或空
    public static boolean isMapEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    // 集合工具：检查数组是否为null或空
    public static boolean isArrayEmpty(Object[] array) {
        return array == null || array.length == 0;
    }
}
