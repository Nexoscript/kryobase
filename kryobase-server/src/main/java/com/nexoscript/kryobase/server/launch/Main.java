import com.nexoscript.kryobase.api.server.KryobaseServerProvider;
import com.nexoscript.kryobase.server.KryobaseServer;
import com.nexoscript.kryobase.server.routes.InfoRoute;

void main() {
    KryobaseServer kryobaseServer = new KryobaseServer();
    KryobaseServerProvider.register(kryobaseServer);
    kryobaseServer.getRouteHandler().register(new InfoRoute());
    kryobaseServer.start("localhost", 8080);
}