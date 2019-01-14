import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBankcardInfo } from 'app/shared/model/bankcard-info.model';

@Component({
    selector: 'jhi-bankcard-info-detail',
    templateUrl: './bankcard-info-detail.component.html'
})
export class BankcardInfoDetailComponent implements OnInit {
    bankcardInfo: IBankcardInfo;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bankcardInfo }) => {
            this.bankcardInfo = bankcardInfo;
        });
    }

    previousState() {
        window.history.back();
    }
}
