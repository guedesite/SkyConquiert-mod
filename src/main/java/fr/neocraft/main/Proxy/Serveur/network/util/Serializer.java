package fr.neocraft.main.Proxy.Serveur.network.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;


public class Serializer {
    public static String toString( Serializable o ){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream( baos );
            oos.writeObject( o );
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
        	e.printStackTrace();
            return "";
        }
    }

 

    public static Object fromString( ByteBuf u ){
       byte [] data = Base64.getDecoder().decode( ByteBufUtils.readUTF8String(u));
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream( new ByteArrayInputStream( data ) );
            Object o;
            o = ois.readObject();
            ois.close();
            return o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
        	e.printStackTrace();

            return null;

        }

    }
}
