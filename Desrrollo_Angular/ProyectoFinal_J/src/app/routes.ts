import {Routes} from '@angular/router';
import { PrincipalComponent } from './principal/principal.component';


const routeConfig: Routes = [
  {
    path: '',
    component: PrincipalComponent,
    title: 'Home page',
  },
  
];
export default routeConfig;