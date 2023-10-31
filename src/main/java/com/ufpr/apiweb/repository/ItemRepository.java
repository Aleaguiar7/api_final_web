package com.ufpr.apiweb.repository;

import com.ufpr.apiweb.model.Item;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    // Método personalizado para inserir um item no banco de dados
	@Transactional
    Item save(int id_item);	
	
	Item findByid(int i);
	
	@Modifying
    @Transactional
    @Query("UPDATE Item i SET i.nome_item = :nomeItem, i.quantia = :quantia WHERE i.id_item = :id")
    void updateItemById(@Param("id") int id, @Param("nomeItem") String nomeItem, @Param("quantia") int quantia);


    // Método personalizado para excluir um item do banco de dados por ID
	@Transactional
    void deleteById(int id_item);
}
