package com.example.service;

import java.util.List;
import java.util.Optional;


public interface CrudService<T, ID> {

    /**
     * Sauvegarde une entité dans la base de données.
     * (Insertion si nouvelle entité, mise à jour si elle existe déjà)
     *
     * @param entity entité à sauvegarder
     * @return l'entité persistée
     */
    T save(T entity);

    /**
     * Recherche une entité par son identifiant.
     *
     * @param id identifiant de l'entité
     * @return Optional contenant l'entité si trouvée, sinon Optional.empty()
     */
    Optional<T> findById(ID id);

    /**
     * Récupère toutes les entités du type T.
     *
     * @return liste de toutes les entités
     */
    List<T> findAll();

    /**
     * Met à jour une entité existante dans la base de données.
     *
     * @param entity entité à mettre à jour
     */
    void update(T entity);

    /**
     * Supprime une entité de la base de données.
     *
     * @param entity entité à supprimer
     */
    void delete(T entity);

    /**
     * Supprime une entité à partir de son identifiant.
     *
     * @param id identifiant de l'entité à supprimer
     */
    void deleteById(ID id);
}