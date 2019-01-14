/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DtlAppMockTestModule } from '../../../test.module';
import { EnterpriseInfoDeleteDialogComponent } from 'app/entities/enterprise-info/enterprise-info-delete-dialog.component';
import { EnterpriseInfoService } from 'app/entities/enterprise-info/enterprise-info.service';

describe('Component Tests', () => {
    describe('EnterpriseInfo Management Delete Component', () => {
        let comp: EnterpriseInfoDeleteDialogComponent;
        let fixture: ComponentFixture<EnterpriseInfoDeleteDialogComponent>;
        let service: EnterpriseInfoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [EnterpriseInfoDeleteDialogComponent]
            })
                .overrideTemplate(EnterpriseInfoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnterpriseInfoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnterpriseInfoService);
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
