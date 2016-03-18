package com.ithejas.repository;

import org.bson.types.ObjectId;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ithejas.model.UserModel;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query; 

@Component
public class UserRepositoryImpl extends BasicDAO<UserModel, ObjectId>
{
    @Autowired
    public UserRepositoryImpl( Datastore datastore )
    {
        super( datastore );
    }
 
    public UserModel findUserByUsername( String username )
    {
        Query<UserModel> query = createQuery();
        query.field( "username" ).equal( username );
        return find( query ).get();
    }
}