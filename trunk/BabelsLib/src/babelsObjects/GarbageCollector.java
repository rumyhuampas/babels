/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package babelsObjects;

public class GarbageCollector{


/**
* getSolicitaGarbageColector Método que invoca al Garbage Colector( NO LO FUERZA a venir, 
* envia una solicitud a la JVM para su proceso..!!! )
*
*/
public static void getSolicitaGarbageColector(){

try{ 
System.out.println( "********** INICIO: 'LIMPIEZA GARBAGE COLECTOR' **********" );
Runtime basurero = Runtime.getRuntime(); 
System.out.println( "MEMORIA TOTAL 'JVM': " + basurero.totalMemory() );
System.out.println( "MEMORIA [FREE] 'JVM' [ANTES]: " + basurero.freeMemory() );
basurero.gc(); //Solicitando ... 
System.out.println( "MEMORIA [FREE] 'JVM' [DESPUES]: " + basurero.freeMemory() );
System.out.println( "********** FIN: 'LIMPIEZA GARBAGE COLECTOR' **********" );
}
catch( Exception e ){
e.printStackTrace();
} 
}
}
