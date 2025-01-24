package com.javashark.puntosdeventaapi.utilities;

import com.javashark.puntosdeventaapi.model.PuntoVentaCosto;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

// ESTE ALGORITMO ME LO DESARROLLO LA INTERNET :D
public class DijkstraAlgorithm {

    public static Map<String, Object> dijkstra(Long start, Long end, ConcurrentMap<String, PuntoVentaCosto> cache) {
        // Convertir cache a grafo
        Map<Long, List<int[]>> graph = new HashMap<>();
        for (PuntoVentaCosto costo : cache.values()) {
            graph.computeIfAbsent(costo.getIdA(), k -> new ArrayList<>())
                    .add(new int[]{costo.getIdB().intValue(), costo.getCosto()});
            graph.computeIfAbsent(costo.getIdB(), k -> new ArrayList<>())
                    .add(new int[]{costo.getIdA().intValue(), costo.getCosto()}); // Grafo no dirigido
        }

        // Inicialización
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // [nodo, costo acumulado]
        Map<Long, Integer> distances = new HashMap<>(); // Nodo -> Distancia mínima conocida
        Map<Long, Long> previous = new HashMap<>(); // Nodo -> Nodo anterior en el camino mínimo
        pq.offer(new int[]{start.intValue(), 0});
        distances.put(start, 0);

        // Algoritmo de Dijkstra
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int cost = current[1];

            // Si alcanzamos el nodo de destino, terminamos
            if ((long) node == end) {
                break;
            }

            // Ignorar caminos más largos que ya conocemos
            if (cost > distances.getOrDefault((long) node, Integer.MAX_VALUE)) {
                continue;
            }

            // Explorar los vecinos
            for (int[] neighbor : graph.getOrDefault((long) node, Collections.emptyList())) {
                int nextNode = neighbor[0];
                int edgeCost = neighbor[1];
                int newCost = cost + edgeCost;

                if (newCost < distances.getOrDefault((long) nextNode, Integer.MAX_VALUE)) {
                    distances.put((long) nextNode, newCost);
                    previous.put((long) nextNode, (long) node); // Registrar de dónde venimos
                    pq.offer(new int[]{nextNode, newCost});
                }
            }
        }

        // Construir el camino desde el mapa 'previous'
        List<Long> path = new ArrayList<>();
        Long current = end;
        while (current != null && previous.containsKey(current)) {
            path.add(0, current); // Agregar al inicio de la lista
            current = previous.get(current);
        }

        // Agregar el nodo de inicio al camino si se encontró una conexión
        if (!path.isEmpty() && path.get(0) != start) {
            path.add(0, start);
        }

        // Verificar si el nodo final es alcanzable
        if (distances.getOrDefault(end, Integer.MAX_VALUE) == Integer.MAX_VALUE) {
            return Map.of(
                    "cost", -1,
                    "path", Collections.emptyList()
            );
        }

        return Map.of(
                "cost", distances.get(end),
                "path", path
        );
    }
}
