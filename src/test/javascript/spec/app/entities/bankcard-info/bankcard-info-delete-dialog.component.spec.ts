/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DtlAppMockTestModule } from '../../../test.module';
import { BankcardInfoDeleteDialogComponent } from 'app/entities/bankcard-info/bankcard-info-delete-dialog.component';
import { BankcardInfoService } from 'app/entities/bankcard-info/bankcard-info.service';

describe('Component Tests', () => {
    describe('BankcardInfo Management Delete Component', () => {
        let comp: BankcardInfoDeleteDialogComponent;
        let fixture: ComponentFixture<BankcardInfoDeleteDialogComponent>;
        let service: BankcardInfoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [BankcardInfoDeleteDialogComponent]
            })
                .overrideTemplate(BankcardInfoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BankcardInfoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BankcardInfoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
