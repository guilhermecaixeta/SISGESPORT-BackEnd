import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServidorEtapa1Component } from './servidor-etapa1.component';

const routes: Routes = [
    {
        path: '', component: ServidorEtapa1Component
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ServidorEtapa1RoutingModule {
}
