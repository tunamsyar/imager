import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Image } from '../models/image.model';

const baseUrl = 'http://localhost:8080/api/images';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient) {}

  getAll(params: any): Observable<any> {
    return this.http.get<any>(baseUrl, { params })
  }

  getImage(id: string) {
    return this.http.get<Image>(`${baseUrl}/${id}`)
  }

  deleteImage(id: string) {
    return this.http.delete<any>(`${baseUrl}/${id}`)
  }
}

