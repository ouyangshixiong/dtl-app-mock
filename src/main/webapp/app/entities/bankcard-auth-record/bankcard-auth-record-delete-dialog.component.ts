import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';
import { BankcardAuthRecordService } from './bankcard-auth-record.service';

@Component({
    selector: 'jhi-bankcard-auth-record-delete-dialog',
    templateUrl: './bankcard-auth-record-delete-dialog.component.html'
})
export class BankcardAuthRecordDeleteDialogComponent {
    bankcardAuthRecord: IBankcardAuthRecord;

    constructor(
        protected bankcardAuthRecordService: BankcardAuthRecordService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bankcardAuthRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'bankcardAuthRecordListModification',
                content: 'Deleted an bankcardAuthRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-bankcard-auth-record-delete-popup',
    template: ''
})
export class BankcardAuthRecordDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bankcardAuthRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BankcardAuthRecordDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.bankcardAuthRecord = bankcardAuthRecord;
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
