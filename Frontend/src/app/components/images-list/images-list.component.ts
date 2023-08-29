import { Component, OnInit } from '@angular/core';
import { Image } from '../../models/image.model';
import { ImageService } from '../../services/image.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-images-list',
  templateUrl: './images-list.component.html',
  styleUrls: ['./images-list.component.scss']
})
export class ImagesListComponent implements OnInit {
  images: Image[] = [];
  currentImage: Image = {};
  currentIndex = -1;
  title = '';

  page = 1;
  count = 0;
  pageSize = 9;
  pageSizes = [3, 6, 9];

  constructor(
    private imageService: ImageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.retrieveImages();
  }

  getRequestParams(page: number, pageSize: number): any{
    let params: any = {};

    if (page) {
      params[`page`] = page - 1;
    }

    if (pageSize) {
      params[`size`] = pageSize;
    }

    return params
  }

  retrieveImages(): void {
    const params = this.getRequestParams(this.page, this.pageSize);

    this.imageService.getAll(params)
      .subscribe(
        response => {
          const { images, totalItems } = response;
          this.images = images;
          this.count = totalItems;
          console.log(response);
        },
        error => {
          console.error(error);
        }
      );
  }

  handleToUpload(): void{
    this.router.navigate(["/upload"]);
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.retrieveImages();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retrieveImages();
  }
}
