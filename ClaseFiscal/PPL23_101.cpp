#include "PPL23_101.h"


std::string ImpresorFiscalPPL23_101::Version ("HASAR SMH/PL-23F V: 01.01" /* " - 20/06/2008" */);


// ############################################################ //
//																//
//			M E T O D O S      L O C A L E S					//
//																//
// ############################################################ //

//
// Constructor
//
ImpresorFiscalPPL23_101::ImpresorFiscalPPL23_101 ()
{
	// Inicialización de variables de uso general

	// Inicialización de variables de tamaño de campos

	// Seteamos modelo con anomalía de comunicaciones ...
	Protocolo->AnomaliaComunicaciones(true);
}


//
// Obtener la Descripción del Modelo Seleccionado
//
std::string
ImpresorFiscalPPL23_101::DescripcionModelo () const
{
	return 	(Version);
}


// ############################################################ //
//																//
//			M E T O D O S   G E N E R A L E S					//
//																//
// ############################################################ //



// ############################################################ //
//																//
//			M E T O D O S   D E    C O N F I G U R A C I O N	//
//																//
// ############################################################ //



// ############################################################ //
//																//
//			M E T O D O S   D E    R E P O R T E S				//
//																//
// ############################################################ //



// ############################################################ //
//																//
//			M E T O D O S   D E    D F							//
//																//
// ############################################################ //



// ############################################################ //
//																//
//			M E T O D O S   D E    D N F						//
//																//
// ############################################################ //



// ############################################################ //
//																//
//			M E T O D O S   D E    D N F H						//
//																//
// ############################################################ //



