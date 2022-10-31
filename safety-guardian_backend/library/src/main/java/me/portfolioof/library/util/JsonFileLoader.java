//package me.portfolioof.library.util;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.core.io.ClassPathResource;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//public class JsonFileLoader {
//   public void load(CrimeDataDAO crimeDataDAO) {
//       ObjectMapper mapper = new ObjectMapper();
//       Map<String, Object> jsonObject = mapper.readValue(new ClassPathResource("crime_data_coor.geojson").getInputStream(), Map.class);
//       List<Map<String, Object>> features = (List<Map<String, Object>>) jsonObject.get("features");
//
//       Map<Long, Iterable<Double>> coorDatas = new HashMap<>(features.size());
//       features.forEach(map -> {
//           Map<String, Object> properties = (Map<String, Object>) map.get("properties");
//           long id = (int) properties.get("ObjectID");
//           Map<String, Object> geometry = (Map<String, Object>) map.get("geometry");
//           Iterable<Double> coordinates = null;
//           if (geometry != null) {
//               coordinates = ((Iterable<Double>) geometry.get("coordinates"));
//           }
//           coorDatas.put(id, coordinates);
//
//       });
//       Iterable<CrimeData> crimeDatas = crimeDataDAO.findAll();
//       for (CrimeData data : crimeDatas) {
//
//           Iterable<Double> iterable = coorDatas.get(data.getId());
//           if (iterable != null) {
//               Iterator<Double> it = iterable.iterator();
//               data.setX(it.next());
//               data.setY(it.next());
//           }
//       }
//       crimeDataDAO.saveAll(crimeDatas);
//   }
//}
