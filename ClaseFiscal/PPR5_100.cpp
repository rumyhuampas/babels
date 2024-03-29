#include "PPR5_100.h"


std::string ImpresorFiscalPPR5_100::Version ("SMH/P-PR5F - Versi�n 1.00");


// ############################################################ //
//																//
//			M E T O D O S      L O C A L E S					//
//																//
// ############################################################ //

//
// Constructor
//
ImpresorFiscalPPR5_100::ImpresorFiscalPPR5_100 ()
{
	// Inicializaci�n de variables de uso general

	// Inicializaci�n de variables de tama�o de campos

}


//
// Obtener la Descripci�n del Modelo Seleccionado
//
std::string
ImpresorFiscalPPR5_100::DescripcionModelo () const
{
	return 	(Version);
}


// ############################################################ //
//																//
//			M E T O D O S   G E N E R A L E S					//
//																//
// ############################################################ //

//
// Cargar Datos del Cliente
//
void
ImpresorFiscalPPR5_100::DatosCliente (
						const std::string &RazonSocial,
						const std::string &NroDocumento,
						TiposDeDocumentoCliente TipoDocumento,
						TiposDeResponsabilidadesCliente ResponsabilidadIVA,
						const std::string &Direccion) throw(Excepcion)
{
	// printf ("Comando DatosCliente ejecutado en PPR5_100\n");

	assert(RazonSocialSize != 0);
	assert(DireccionSize != 0);

	// Verificamos la existencia del par�metro direcci�n
	// (es obligatorio para este modelo)
	if (Direccion.length() == 0)
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_PARAMETRO_OPCIONAL, "Cargar Datos del Cliente");

	// Enviamos el comando fiscal y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_SET_CUSTOMER_DATA) + FS +
						Cadena (RazonSocial, RazonSocialSize) + FS +
						Cadena (NroDocumento, 11) + FS +
						Caracter (ResponsabilidadIVA) + FS +
						Caracter (TipoDocumento) + FS +
						Cadena (Direccion, DireccionSize));
}


// ############################################################ //
//																//
//			M E T O D O S   D E    C O N F I G U R A C I O N	//
//																//
// ############################################################ //

//
// Configurar Controlador por Bloque de Par�metros
//
void
ImpresorFiscalPPR5_100::ConfigurarControladorPorBloque (
							double LimiteConsumidorFinal,
							double LimiteTicketFactura,
							double PorcentajeIVANoInscripto,
							NumerosDeCopias TipoDeCopiasMaximo,
							bool ImpresionCambio,
							bool ImpresionLeyendasOpcionales,
							TiposDeCorteDePapel TipoDeCorte) throw (Excepcion)
{
    // printf ("Comando ConfigurarControladorPorBloque ejecutado en PPR5_100\n");

	// Si el TipoDeCopiasMaximo es NO_COPIAS lo corregimos a ORIGINAL
	// (NO_COPIAS no es un valor v�lido para este modelo) ...
	if (TipoDeCopiasMaximo == NO_COPIAS)
		TipoDeCopiasMaximo = ORIGINAL;

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal'
	ImpresorFiscal::ConfigurarControladorPorBloque (
							LimiteConsumidorFinal,
							LimiteTicketFactura,
							PorcentajeIVANoInscripto,
							TipoDeCopiasMaximo,
							ImpresionCambio,
							ImpresionLeyendasOpcionales,
							TipoDeCorte);
}


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


