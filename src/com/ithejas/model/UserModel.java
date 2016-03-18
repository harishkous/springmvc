package com.ithejas.model;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
 
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
 
@Entity( value="users", noClassnameStored=true, concern="SAFE" )
public class UserModel
{
    @Id
    private ObjectId id;
    @Indexed(unique=true)
    
    private String username;
    private String password;
    private String role;
 
    protected UserModel() {}
 
    public UserModel( String username, String password, String role )
    {
        this.username = username;
        this.password = DigestUtils.sha1Hex( password );
        this.role = role;
    }
 
    public ObjectId getId() { return id; }
    public String getUsername() { return username; }
    public String getSHA1Password() { return password; }
    public String getRole() { return role; }
    
    public String toString(){
    	return "[id:"+getId()+",username:"+getUsername()+",pass:"+getSHA1Password()+",role:"+getRole()+"]";
    }
}
