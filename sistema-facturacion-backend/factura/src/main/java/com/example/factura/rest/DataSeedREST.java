package com.example.factura.rest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.factura.model.Producto;
import com.example.factura.model.Sucursal;
import com.example.factura.model.Ticket;
import com.example.factura.model.TipoPago;
import com.example.service.ProductoService;
import com.example.service.SucursalService;
import com.example.service.TicketService;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/dataseed")
public class DataSeedREST {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SucursalService sucursalService;

    private Producto producto1 = new Producto("Monitor", 2600);
    private Producto producto2 = new Producto("Teclado", 1500);
    private Producto producto3 = new Producto("Mouse", 2500);
    private Producto producto4 = new Producto("Memoria RAM", 1000);
    private Producto producto5 = new Producto("Procesador", 3000);
    private Producto producto6 = new Producto("Gabinete", 1200);
    private Producto producto7 = new Producto("RTX 4090", 8600);

    private Sucursal sucursal1 = new Sucursal("Tienda Mayo");
    private Sucursal sucursal2 = new Sucursal("Tienda Bellas artes");
    private Sucursal sucursal3 = new Sucursal("Tienda Reforma");
    private Sucursal sucursal4 = new Sucursal("Tienda Jalisco");
    private Sucursal sucursal5 = new Sucursal("Tienda Monterrey");

    private Date date1 = createDate(2022, Calendar.JANUARY, 10);
    private Date date2 = createDate(2022, Calendar.FEBRUARY, 15);
    private Date date3 = createDate(2022, Calendar.MARCH, 20);
    private Date date4 = createDate(2022, Calendar.APRIL, 25);
    private Date date5 = createDate(2022, Calendar.MAY, 30);

    private Long folio1 = 1234567890L;
    private Long folio2 = 987654321L;
    private Long folio3 = 1122334455L;
    private Long folio4 = 5566778899L;
    private Long folio5 = 6677889900L;

    private Long codigoFacturacion1 = 123456L;
    private Long codigoFacturacion2 = 234567L;
    private Long codigoFacturacion3 = 345678L;
    private Long codigoFacturacion4 = 456789L;
    private Long codigoFacturacion5 = 567890L;

    private List<Producto> productos1 = Arrays.asList(producto1, producto2, producto3);
    private List<Producto> productos2 = Arrays.asList(producto4, producto5);
    private List<Producto> productos3 = Arrays.asList(producto6, producto7);
    private List<Producto> productos4 = Arrays.asList(producto1, producto3, producto5, producto7);
    private List<Producto> productos5 = Arrays.asList(producto2, producto4, producto6);

    private Ticket ticket1 = new Ticket(date1, folio1, codigoFacturacion1, sucursal1, 6600, 660, TipoPago.TARJETA,
            productos1);
    private Ticket ticket2 = new Ticket(date2, folio2, codigoFacturacion2, sucursal2, 4000, 400, TipoPago.EFECTIVO,
            productos2);
    private Ticket ticket3 = new Ticket(date3, folio3, codigoFacturacion3, sucursal3, 9800, 980, TipoPago.TARJETA,
            productos3);
    private Ticket ticket4 = new Ticket(date4, folio4, codigoFacturacion4, sucursal4, 16700, 1670, TipoPago.TARJETA,
            productos4);
    private Ticket ticket5 = new Ticket(date5, folio5, codigoFacturacion5, sucursal5, 3700, 370, TipoPago.EFECTIVO,
            productos5);

    @PostMapping
    private ResponseEntity<String> saveDataSeed() {

        return saveAllData();
    }

    private ResponseEntity<String> saveAllData() {
        try {
            productoService.saveAll(
                    Arrays.asList(producto1, producto2, producto3, producto4, producto5, producto6, producto7));
            sucursalService.saveAll(Arrays.asList(sucursal1, sucursal2, sucursal3, sucursal4, sucursal5));
            ticketService.saveAll(Arrays.asList(ticket1, ticket2, ticket3, ticket4, ticket5));
            return ResponseEntity.ok("Datos de prueba guardados exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al guardar los datos de prueba: " + e.getMessage());
        }
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
