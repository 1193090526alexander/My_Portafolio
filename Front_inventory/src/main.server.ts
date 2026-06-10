import { BootstrapContext, bootstrapApplication, provideClientHydration } from '@angular/platform-browser';
import { App } from './app/app';
import { config } from './app/app.config.server';
import 'zone.js/node';

const bootstrap = (context: BootstrapContext) =>
    bootstrapApplication(App, {
      providers: [
        ...config.providers,
        provideClientHydration()   // 👈 necesario en el servidor
      ]
    }, context);

export default bootstrap;
