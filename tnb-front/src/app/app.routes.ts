import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { TerrainComponent } from './pages/terrain/terrain.component';
import { CategoryComponent } from './pages/category/category.component';
import { TaxeComponent } from './pages/taxe/taxe.component';
import { AuthGuard } from './quards/auth.guard';

export const routes: Routes = [
  { path: '', component: DashboardComponent },
  {
    path: 'terrain',
    component: TerrainComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'category',
    component: CategoryComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ADMIN'] },
  },
  {
    path: 'taxe',
    component: TaxeComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ADMIN'] },
  },
];
