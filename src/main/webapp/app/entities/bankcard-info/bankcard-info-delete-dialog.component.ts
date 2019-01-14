import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBankcardInfo } from 'app/shared/model/bankcard-info.model';
import { BankcardInfoService } from './bankcard-info.service';

@Component({
    selector: 'jhi-bankcard-info-delete-dialog',
    templateUrl: './bankcard-info-delete-dialog.component.html'
})
export class BankcardInfoDeleteDialogComponent {
    bankcardInfo: IBankcardInfo;

    constructor(
        protected bankcardInfoService: BankcardInfoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bankcardInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'bankcardInfoListModification',
                content: 'Deleted an bankcardInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-bankcard-info-delete-popup',
    template: ''
})
export class BankcardInfoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bankcardInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BankcardInfoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.bankcardInfo = bankcardInfo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
