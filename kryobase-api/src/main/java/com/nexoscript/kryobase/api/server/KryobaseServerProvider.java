package com.nexoscript.kryobase.api.server;

import com.nexoscript.kryobase.api.exception.KryobaseExistingProviderInstanceException;

public class KryobaseServerProvider {
    private static IKryobaseServer server;

    public static void register(IKryobaseServer server) throws KryobaseExistingProviderInstanceException {
        if (KryobaseServerProvider.server != null) {
            throw new KryobaseExistingProviderInstanceException("Server has already been registered.");
        }
        KryobaseServerProvider.server = server;
    }

    public static IKryobaseServer get() {
        return KryobaseServerProvider.server;
    }
}
