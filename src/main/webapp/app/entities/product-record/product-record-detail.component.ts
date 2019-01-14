import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductRecord } from 'app/shared/model/product-record.model';

@Component({
    selector: 'jhi-product-record-detail',
    templateUrl: './product-record-detail.component.html'
})
export class ProductRecordDetailComponent implements OnInit {
    productRecord: IProductRecord;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ productRecord }) => {
            this.productRecord = productRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
