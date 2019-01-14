import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRealnameInfo } from 'app/shared/model/realname-info.model';

@Component({
    selector: 'jhi-realname-info-detail',
    templateUrl: './realname-info-detail.component.html'
})
export class RealnameInfoDetailComponent implements OnInit {
    realnameInfo: IRealnameInfo;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ realnameInfo }) => {
            this.realnameInfo = realnameInfo;
        });
    }

    previousState() {
        window.history.back();
    }
}
