import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ImagesListComponent } from './components/images-list/images-list.component';
import { ImageShowComponent } from './components/image-show/image-show.component';
import { FileUploadComponent } from './components/file-upload/file-upload.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: "", component: HomeComponent },
  { path: 'images', component: ImagesListComponent },
  { path: 'images/:id', component: ImageShowComponent },
  { path: 'upload', component: FileUploadComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
