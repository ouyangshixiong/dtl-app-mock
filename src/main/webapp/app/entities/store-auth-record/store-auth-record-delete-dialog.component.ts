import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStoreAuthRecord } from 'app/shared/model/store-auth-record.model';
import { StoreAuthRecordService } from './store-auth-record.service';

@Component({
    selector: 'jhi-store-auth-record-delete-dialog',
    templateUrl: './store-auth-record-delete-dialog.component.html'
})
export class StoreAuthRecordDeleteDialogComponent {
    storeAuthRecord: IStoreAuthRecord;

    constructor(
        protected storeAuthRecordService: StoreAuthRecordService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.storeAuthRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'storeAuthRecordListModification',
                content: 'Deleted an storeAuthRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-store-auth-record-delete-popup',
    template: ''
})
export class StoreAuthRecordDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ storeAuthRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StoreAuthRecordDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.storeAuthRecord = storeAuthRecord;
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
