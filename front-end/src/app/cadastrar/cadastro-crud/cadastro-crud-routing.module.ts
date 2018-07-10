import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CadastroCrudComponent } from './cadastro-crud.component';

const routes: Routes = [
    {
        path: '', component: CadastroCrudComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CadastroCrudRoutingModule {
}
