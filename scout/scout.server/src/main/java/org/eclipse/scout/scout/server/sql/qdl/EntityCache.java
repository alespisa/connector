package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.scout.shared.entities.common.Table;

import java.util.*;

@ApplicationScoped
public class EntityCache {

  private final Map<Class<? extends Table>, Map<List<Object>, ? extends Table>> m_cache = new HashMap<>();
  private final Map<Class<? extends Table>, Map<QDL.Condition, Object[][]>> m_conditionToPrimaryKeyValuesCache = new HashMap<>();

  @SuppressWarnings("unchecked")
  public <T extends Table> T opt(Class<T> entityClass, Object... primaryKey) {
    List<Object> pkList = Collections.unmodifiableList(Arrays.asList(primaryKey));

    if (m_cache.containsKey(entityClass)) {
      if (m_cache.get(entityClass).containsKey(pkList)) {
        return (T) m_cache.get(entityClass).get(pkList);
      }
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public <T extends Table> void put(T entity, Class<T> entityClass, Object... primaryKey) {
    List<Object> pkList = Collections.unmodifiableList(Arrays.asList(primaryKey));

    Map<List<Object>, T> map;
    if (m_cache.containsKey(entityClass)) {
      map = (Map<List<Object>, T>) m_cache.get(entityClass);
    }
    else {
      map = new HashMap<>();
      m_cache.put(entityClass, map);
    }
    map.put(pkList, entity);
  }

  public <T extends Table> Object[][] optCondition(Class<T> entityClass, QDL.Condition condition) {
    if (m_conditionToPrimaryKeyValuesCache.containsKey(entityClass)) {
      if (m_conditionToPrimaryKeyValuesCache.get(entityClass).containsKey(condition)) {
        return m_conditionToPrimaryKeyValuesCache.get(entityClass).get(condition);
      }
    }
    return null;
  }

  public <T extends Table> void putCondition(Class<T> entityClass, QDL.Condition condition, Object[][] primaryKeyValues) {
    Map<QDL.Condition, Object[][]> map;
    if (m_conditionToPrimaryKeyValuesCache.containsKey(entityClass)) {
      map = m_conditionToPrimaryKeyValuesCache.get(entityClass);
    }
    else {
      map = new HashMap<>();
      m_conditionToPrimaryKeyValuesCache.put(entityClass, map);
    }
    map.put(condition, primaryKeyValues);
  }

  public <T extends Table> void clear(Class<T> entityClass) {
    if (entityClass == null) {
      return;
    }

    if (m_cache.containsKey(entityClass)) {
      m_cache.get(entityClass).clear();
    }
    if (m_conditionToPrimaryKeyValuesCache.containsKey(entityClass)) {
      m_conditionToPrimaryKeyValuesCache.get(entityClass).clear();
    }
  }

  public void clearAll() {
    m_cache.clear();
    m_conditionToPrimaryKeyValuesCache.clear();
  }

  @Override
  public String toString() {
    return m_cache.toString();
  }

}
